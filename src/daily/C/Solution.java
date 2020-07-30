package daily.C;

import java.util.*;

/**
 * @auther: 卑微小冯
 * @date: 2020/7/29 14:36
 */

/**
 * LCP 13. 寻宝
 * 我们得到了一副藏宝图，藏宝图显示，在一个迷宫中存在着未被世人发现的宝藏。
 *
 * 迷宫是一个二维矩阵，用一个字符串数组表示。它标识了唯一的入口（用 'S' 表示），和唯一的宝藏地点（用 'T' 表示）。但是，宝藏被一些隐蔽的机关保护了起来。在地图上有若干个机关点（用 'M' 表示），只有所有机关均被触发，才可以拿到宝藏。
 *
 * 要保持机关的触发，需要把一个重石放在上面。迷宫中有若干个石堆（用 'O' 表示），每个石堆都有无限个足够触发机关的重石。但是由于石头太重，我们一次只能搬一个石头到指定地点。
 *
 * 迷宫中同样有一些墙壁（用 '#' 表示），我们不能走入墙壁。剩余的都是可随意通行的点（用 '.' 表示）。石堆、机关、起点和终点（无论是否能拿到宝藏）也是可以通行的。
 *
 * 我们每步可以选择向上/向下/向左/向右移动一格，并且不能移出迷宫。搬起石头和放下石头不算步数。那么，从起点开始，我们最少需要多少步才能最后拿到宝藏呢？如果无法拿到宝藏，返回 -1 。
 *
 * 示例 1：
 *
 * 输入： ["S#O", "M..", "M.T"]
 *
 * 输出：16
 *
 * 解释：最优路线为： S->O, cost = 4, 去搬石头 O->第二行的M, cost = 3, M机关触发 第二行的M->O, cost = 3, 我们需要继续回去 O 搬石头。 O->第三行的M, cost = 4, 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。 图片.gif
 *
 * 示例 2：
 *
 * 输入： ["S#O", "M.#", "M.T"]
 *
 * 输出：-1
 *
 * 解释：我们无法搬到石头触发机关
 *
 * 示例 3：
 *
 * 输入： ["S#O", "M.T", "M.."]
 *
 * 输出：17
 *
 * 解释：注意终点也是可以通行的。
 *
 * 限制：
 *
 * 1 <= maze.length <= 100
 * 1 <= maze[i].length <= 100
 * maze[i].length == maze[j].length
 * S 和 T 有且只有一个
 * 0 <= M的数量 <= 16
 * 0 <= O的数量 <= 40，题目保证当迷宫中存在 M 时，一定存在至少一个 O 。
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(minimalSteps(new String[]{"S#O", "M..", "M.T"}));
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m; // 矩阵行数和列数

    public static int minimalSteps(String[] maze) {
        n = maze.length;
        m = maze[0].length();

        // 存放机关和石头的坐标
        List<Integer[]> buttons = new ArrayList<>();
        List<Integer[]> stones = new ArrayList<>();

        // 获取机关、石头、起点、终点
        int sx = -1, sy = -1, tx = -1, ty = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i].charAt(j) == 'M') // 说明是机关
                    buttons.add(new Integer[]{i, j});
                if (maze[i].charAt(j) == 'O') // 说明是石头
                    stones.add(new Integer[]{i, j});
                if (maze[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }
                if (maze[i].charAt(j) == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }

        int nb = buttons.size(); // 机关数
        int ns = stones.size(); // 石头数
        int[][] startDist = bfs(sx, sy, maze); // 获取距离矩阵

        if(nb==0) // 如果没有机关和石头则最短距离可以直接得到
            return startDist[tx][ty];

        // 存放机关和起终点两两之间的最短距离
        int[][] dist = new int[nb][nb+2];
        for (int i = 0; i < nb; i++) {
            Arrays.fill(dist[i],-1);
        }

        // 获取中间结果以每个点为中心的距离矩阵
        int[][][] dd=new int[nb][][];
        for (int i = 0; i < nb; i++) {
            int[][] d = bfs(buttons.get(i)[0], buttons.get(i)[1],maze);
            dd[i] = d;
            dist[i][nb+1] = d[tx][ty]; // 从机关到终点，此过程不需要捡石头，因此可以直接到达
        }

        // 填充机关距离矩阵 dist
        for (int i = 0; i < nb; i++) {
            int tmp = -1;
            for (int j = 0; j < ns; j++) { // 遍历所有石头寻找最短路径
                int midX=stones.get(j)[0], midY=stones.get(j)[1];
                if(dd[i][midX][midY]!=-1 && startDist[midX][midY]!=-1){
                    if(tmp == -1 || tmp>dd[i][midX][midY]+startDist[midX][midY]) // 如果从起点到石头再到此机关的长度小于当前的最短长度则替换
                        tmp=dd[i][midX][midY]+startDist[midX][midY];
                }
            }
            dist[i][nb] = tmp; // 起点到当前机关的最小距离

            // 遍历序号比当前机关序号大的所有序号，来完善机关距离矩阵dist
            for (int j = i+1; j < nb; j++) {
                tmp=-1;
                for (int k = 0; k < ns; k++) { // 遍历所有石头
                    int midX=stones.get(k)[0], midY=stones.get(k)[1];
                    if(dd[i][midX][midY]!=-1 && dd[j][midX][midY]!=-1){
                        if(tmp == -1 || tmp>dd[i][midX][midY]+dd[j][midX][midY]) // 如果从机关i到石头再到机关j的长度小于当前的最短长度则替换
                            tmp=dd[i][midX][midY]+dd[j][midX][midY];
                    }
                }
                dist[i][j]=tmp;
                dist[j][i]=tmp;
            }
        }

        for (int i = 0; i < nb; i++) {
            if(dist[i][nb]==-1 || dist[i][nb+1] ==-1) // 如果存在一个机关无法从起点到达或者无法到达重点，则不可能成功
                return -1;
        }

        // dp 数组， -1 代表没有遍历到
        int[][] dp = new int[1 << nb][nb];
        for (int i = 0; i < 1 << nb; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < nb; i++) {
            dp[1 << i][i] = dist[i][nb];
        }

        // 遍历整个网络，得到所有路径的长度，其中全为1的地址的数组中填写的就为从起点经过所有机关到达最终机关的路径长度
        for (int mask = 1; mask < (1 << nb); mask++) {
            for (int i = 0; i < nb; i++) {
                // 当前 dp 是合法的
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < nb; j++) {
                        // j 不在 mask 里
                        if ((mask & (1 << j)) == 0) {
                            int next = mask | (1 << j);
                            if (dp[next][j] == -1 || dp[next][j] > dp[mask][i] + dist[i][j]) {
                                dp[next][j] = dp[mask][i] + dist[i][j];
                            }
                        }
                    }
                }
            }
        }

        int res = -1;
        int finalMask = (1<<nb)-1;
        for (int i = 0; i < nb; i++) { // 遍历所有结果，并加上从最终机关到达终点的长度，即为该条路径的总长度，选出最短
            if(res==-1 || res > dp[finalMask][i]+dist[i][nb+1])
                res = dp[finalMask][i]+dist[i][nb+1];
        }
        return res;
    }

    /**
     * 图的广度优先遍历
     *
     * @param sx
     * @param sy
     * @param maze
     * @return 距离矩阵
     */
    private static int[][] bfs(int sx, int sy, String[] maze) {
        // 初始化
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(res[i], -1);
        }

        // 以入口为起点
        res[sx][sy] = 0;
        Queue<int[]> queue = new LinkedList<>();

        // 根入队列
        queue.offer(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int curx = p[0], cury = p[1]; // 当前位置坐标
            for (int i = 0; i < 4; i++) {
                int nx = curx + dx[i], ny = cury + dy[i]; // 相邻点的位置
                if (isBound(nx, ny) && maze[nx].charAt(ny) != '#' && res[nx][ny]==-1) { // 判断是不是边界或者墙
                    res[nx][ny] = res[curx][cury] + 1; // 不是则将距离加一
                    queue.offer(new int[]{nx, ny}); // 添加到队列
                }
            }
        }
        return res; // 返回距离矩阵
    }

    /**
     * 是否为边界
     *
     * @param nx
     * @param ny
     * @return true of false
     */
    private static boolean isBound(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
}
