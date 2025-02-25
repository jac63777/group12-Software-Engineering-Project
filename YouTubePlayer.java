import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class YouTubePlayer extends Application {
    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();

        // Replace "VIDEO_ID" with the actual YouTube video ID
        String videoId = "dQw4w9WgXcQ"; // Example: Rick Astley - Never Gonna Give You Up
        String embedUrl = "https://www.youtube-nocookie.com/embed/" + videoId + "?autoplay=1&modestbranding=1&rel=0";

        webView.getEngine().load(embedUrl);
        webView.setPrefSize(800, 600);

        primaryStage.setScene(new Scene(webView));
        primaryStage.setTitle("YouTube Video Player");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
