import java.io.File;
import org.tensorflow.SavedModelBundle;

public class Sentiment {
  public void load(int index) throws Exception {
    File modelFile = new File(
        getClass().getClassLoader().getResource("models/sentiment/FGAOKOEHGN7YFDUKO5KQLBY6IA/saved_model.pb").getFile()
    );

    System.out.println("##########################");
    System.out.println(String.format("#         RUN %2d        #", index));
    System.out.println("##########################");


    SavedModelBundle model = SavedModelBundle.load(modelFile.getParent(), "serve");
    System.out.println(String.format("model load, %s", model.toString()));

    model.close();
  }
}
