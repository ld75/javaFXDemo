package application;

import application.controllerfactories.Constants;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import renault.diversitysimulator.application.controllers.IController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils
{

    public static String printStacktrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        pw.write(e.getMessage()!=null?e.getMessage():"Error is:");
        StackTraceElement[] stack = e.getStackTrace();
        for (int i=0; i<stack.length; i++)
        {
            pw.write(stack[i].getClassName()+" : "+stack[i].getMethodName()+" : "+stack[i].getLineNumber()+"\n");
        }
        pw.flush();
        sw.flush(); //Flushes this output stream and forces any buffered output bytes to be written out.
        return sw.toString();
    }

    public static String printStackTraceOnLogs(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();

    }


    public static void openANewPannel(String title, double width, double height, IController controller) throws Exception
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle(title);
        StackPane root = new StackPane() ;
        root.getChildren().addAll(controller.execute()) ;
        Scene scene = new Scene(root,width,height) ;
        primaryStage.setScene(scene) ;
        primaryStage.show() ;
    }

    public static void openErrorDialogPretty(Exception e1)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(e1.getMessage());
        alert.setContentText(Utils.printStacktrace(e1));
        alert.showAndWait();
    }
    public static void openErrorDialog(Exception e1)
    {
        Dialog dialog = new Dialog();
        DialogPane dialogEmbeded = new DialogPane();
        dialogEmbeded.setContent(new ScrollPane(new TextArea(Utils.printStacktrace(e1))));
        dialog.setDialogPane(dialogEmbeded);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        dialog.setWidth(900);
        dialog.setHeight(900);
        dialog.show();
    }
    public static void openMessageDialog(String msg) throws UnsupportedEncodingException
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFO");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    public static void openWarningDialog(String alertDialogTitle, String alertDialogHeader, String alertDialogContexte) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(alertDialogTitle);
        alert.setHeaderText(alertDialogHeader);
        alert.setContentText(alertDialogContexte);

        alert.showAndWait();
    }

    public static Optional openConfirmationDialog(String alertDialogTitle, String alertDialogHeader, String alertDialogContexte) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(alertDialogTitle);
        alert.setHeaderText(alertDialogHeader);
        alert.setContentText(alertDialogContexte);

        return alert.showAndWait();
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static String getPercentageFormat(Double numberToFormat){

        return  Constants.decimalPercentageFormat.format(numberToFormat*100) + "%";
    }

    public static String fromPercentageFormatToSimpleStringNumber(String chaine){

        chaine = chaine.trim().replaceAll("\\s|%","");
        chaine = chaine.trim().replaceAll(",",".");

        return chaine;
    }

    public static boolean checkPercentageFormat(String percentageNewValue){

        percentageNewValue = Utils.fromPercentageFormatToSimpleStringNumber(percentageNewValue);
        String splitedWrongRevenuesFormat[] = percentageNewValue.split("\\.");

        if((splitedWrongRevenuesFormat.length ==1 || splitedWrongRevenuesFormat.length ==2 || "".equals(percentageNewValue)
                && !".".equals(percentageNewValue))){

            if("".equals(percentageNewValue)){
                return true;
            }else{
                for(int i=0; i <= splitedWrongRevenuesFormat.length-1;i++){
                    if(!Utils.isNumeric(splitedWrongRevenuesFormat[i])){
                        Utils.openWarningDialog("WARNING...",
                                "PERCENTAGE FIELD CAN ONLY CONTAIN A NUMBER",
                                "Please close this window and enter a correct : Ex: 100% - 1% - 0,14%");
                        return false;
                    }
                }

                if(Double.parseDouble(splitedWrongRevenuesFormat[0]) < 0 || Double.parseDouble(splitedWrongRevenuesFormat[0]) > 100){
                    Utils.openWarningDialog("WARNING...",
                            "PERCENTAGE MUST BE A NUMBER BETWEEN 1 AND 100",
                            "Please close this window and enter a correct : Ex: 100% - 1% - 0,14%");
                    return false;
                }
            }
        }else{
            Utils.openWarningDialog("WARNING...",
                    "PERCENTAGE FIELD CAN ONLY CONTAIN A NUMBER",
                    "Please close this window and enter a correct : Ex: 100% - 1% - 0,14%");
            return false;
        }
        return true;
    }


    public static boolean isCorrectPercentage(String percentage) {
        return percentage.contains("%") || "".equals(Utils.fromPercentageFormatToSimpleStringNumber(percentage));
    }

    static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    System.err.println(
                            "Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    public static <T,R, E extends Exception> Function<T,R> handlingFunctionWrapper(ThrowingFunction<T, R, E> throwingConsumer, Class<E> exceptionClass) {
        return i -> {
            try {
                return throwingConsumer.apply(i);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    throw new RuntimeException(exCast);
                } catch (ClassCastException ccEx) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }


    public static class AnimatedImage
    {
        private static final int OFFSET_X = 0;
        private static final int OFFSET_Y = 0;

        public AnimatedImage(int nbColumns, int nbFrames, int widthSpritePixel, int heightSpritePixel, Double speed, int nbrepet, ImageView imageView)
        {
            final Animation animation = new SpriteAnimation(
                    imageView,
                    Duration.millis(speed),
                    nbFrames, nbColumns,
                    OFFSET_X, OFFSET_Y,
                    widthSpritePixel, heightSpritePixel
            );

            animation.setCycleCount(nbrepet);
            animation.play();

        }
        public class SpriteAnimation extends Transition
        {

            private final ImageView imageView;
            private final int count;
            private final int columns;
            private final int offsetX;
            private final int offsetY;
            private final int width;
            private final int height;

            private int lastIndex;

            public SpriteAnimation(
                    ImageView imageView,
                    Duration duration,
                    int count,   int columns,
                    int offsetX, int offsetY,
                    int width,   int height) {
                this.imageView = imageView;
                this.count     = count;
                this.columns   = columns;
                this.offsetX   = offsetX;
                this.offsetY   = offsetY;
                this.width     = width;
                this.height    = height;
                setCycleDuration(duration);
                setInterpolator(Interpolator.LINEAR);
            }

            protected void interpolate(double k) {
                final int index = Math.min((int) Math.floor(k * count), count - 1);
                if (index != lastIndex) {
                    final int x = (index % columns) * width  + offsetX;
                    final int y = (index / columns) * height + offsetY;
                    imageView.setViewport(new Rectangle2D(x, y, width, height));
                    lastIndex = index;
                }
            }
        }
    }
    @FunctionalInterface
    public interface ThrowingConsumer<T, E extends Exception>
    {
        void accept(T t) throws E;
    }

    @FunctionalInterface
    public interface ThrowingFunction<T,R, E extends Exception> {
        R apply(T t) throws E;
    }

}
