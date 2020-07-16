import java.io.File;
import java.util.List;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;

public class DetectObject {

  public void runOnce(int index) throws Exception {
    List<Tensor<?>> outputs = null;

    File modelFile = new File(
        getClass().getClassLoader().getResource("models/objectdetect/saved_model.pb").getFile()
    );

    System.out.println("##########################");
    System.out.println(String.format("#         RUN %2d        #", index));
    System.out.println("##########################");
    try (SavedModelBundle model = SavedModelBundle.load(modelFile.getParent(), "serve")) {

      try {
        outputs = model.session().runner().run();
      } catch (Exception e) {
      } finally {
        if (model != null) {
          model.close();
        }

        if (outputs != null) {
          for (Tensor<?> tensor : outputs) {
            tensor.close();
          }
        }
      }
    }
  }

  public void run() throws Exception {
    int ch, numRuns = 0;
    List<Tensor<?>> outputs = null;

    File modelFile = new File(
        getClass().getClassLoader().getResource("models/objectdetect/saved_model.pb").getFile()
    );

    while ((ch = System.in.read()) != -1) {
      System.out.println("##########################");
      System.out.println(String.format("#         RUN %2d        #", ++numRuns));
      System.out.println("##########################");
      try (SavedModelBundle model = SavedModelBundle.load(modelFile.getParent(), "serve")) {

        try {
          outputs = model.session().runner().run();
        } catch (Exception e) {
        } finally {
          if (model != null) {
            model.close();
          }

          if (outputs != null) {
            for (Tensor<?> tensor : outputs) {
              tensor.close();
            }
          }
        }
      }
    }

  }

}
