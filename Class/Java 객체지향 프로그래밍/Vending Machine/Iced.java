public class Iced {
    public void Iced() {
        System.out.println("플라스틱 컵을 받아주세요.");
        System.out.println("얼음을 받아주세요");
        try {
			Thread.sleep(3000); //3초 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("음료 추출중...");
        try {
			Thread.sleep(3000); //5초 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("음료 추출이 완료되었습니다.");
    }
}
