public class Hot {

    public void Hot() {
        System.out.println("종이컵을 받아주세요.");
        System.out.println("음료 추출중...");
        try {
			Thread.sleep(3000); //5초 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("음료 추출이 완료되었습니다.");
    }
}
