
public class App {

    @FunctionalInterface
    public interface Exec {
        void run(int index) throws Exception;
    }

    public static void main(String[] args) throws Exception {

        //System.out.print("tf hello world:");
        //TfBasics.helloWorld();

        System.out.print("image label classification:");
        //new LabelImageNew().run();
        //new LabelImage().run();

        //testPlan((i) -> new DetectObject().runOnce(i));
        testPlan((i) -> new Sentiment().load(i));
    }

    private static void testPlan(Exec exec) throws Exception{

        int batch = 1000;
        int ch, iteration = 0;
        while ((ch = System.in.read()) != -1) {
            for (int index = iteration*batch + 1; index <= batch; index++) {
                exec.run(index);
            }

            System.gc();
        }
        System.out.println(String.format("# ===  Finish iteration %d  === #", iteration));
    }
}


