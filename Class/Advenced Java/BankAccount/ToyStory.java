public class ToyStory {
    public static void main(String[] args) {
        Toy buzz2 = new SpaceRanger("가짜 버즈");
        Toy buzz = new SpaceRanger("버즈");
        ((SpaceRanger)buzz).pushCapButton();
        Toy woody = new CowboyToy("우디");
        Dino rex = new Dino("Rex");

        woody.say("누가 버즈야?");
        buzz.say("나야!");
        buzz2.say("나야!");
        buzz2.say(woody, "내가 버즈야, z대왕이 날 흉내내서 만든거야");
        ((SpaceRanger)buzz2).pushCapButtoon();

        
    }
}
