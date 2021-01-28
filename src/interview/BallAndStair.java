package interview;


/**
 * 玻璃球和楼层问题：
 * 两个玻璃球，100层楼，最快找出摔碎小球的最低楼层
 * @author just4liz
 *
 */
public class BallAndStair {
	/**
	 * 
	 * @param topStair			最高楼层
	 * @param ballBreak			可以摔碎玻璃球的楼层
	 * @return					找到最低楼层的次数
	 */
	public int calLowerStair(int topStair, int ballBreak) {
		int count = 0;
		int stage = calStage(topStair);
		int nowStair = stage;
		for(;;) {
			if (ballBreak > nowStair) {
				nowStair += --stage;
				count++;
			}else if (nowStair > topStair) {
				break;
			}else{
				++count;
				break;
			}
		}
		
		nowStair = nowStair - stage;
		for(int i = 1; i < stage; i++) {
			count++;
			if (++nowStair >= ballBreak) {
				break;
			}
		}
		return count;
	}
	
	private int calStage(int topStair) {
		int stage = 1;
		int sumStair = 0;
		while(sumStair < topStair) {
			sumStair += stage;
			stage += 1;
		}
		return stage - 1;
	}
	
	public static void main(String[] args) {
		System.out.println(new BallAndStair().calLowerStair(200, 13));
	}
}
