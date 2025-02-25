package assignment.songs;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ouda
 */
public class SongsController implements Initializable {

    @FXML
    private MenuBar mainMenu;
    @FXML
    private ImageView image;
    @FXML
    private BorderPane SongPortal;
    @FXML
    private Label title;
    @FXML
    private Label about;
    @FXML
    private Button play;
    @FXML
    private Button puase;
    @FXML
    private ComboBox size;
    @FXML
    private TextField name;
    Media media;
    MediaPlayer player;
    OrderedDictionary database = null;
    SongRecord song = null;
    int songSize = 1;

    @FXML
    public void exit() {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    public void find() {
        DataKey key = new DataKey(this.name.getText(), songSize);
        try {
            song = database.find(key);
            showSong();
        } catch (DictionaryException ex) {
            displayAlert(ex.getMessage());
        }
    }

    public void delete() {
        SongRecord previousSong = null;
        try {
            previousSong = database.predecessor(song.getDataKey());
        } catch (DictionaryException ex) {

        }
        SongRecord nextSong = null;
        try {
            nextSong = database.successor(song.getDataKey());
        } catch (DictionaryException ex) {

        }
        DataKey key = song.getDataKey();
        try {
            database.remove(key);
        } catch (DictionaryException ex) {
            System.out.println("Error in delete "+ ex);
        }
        if (database.isEmpty()) {
            this.SongPortal.setVisible(false);
            displayAlert("No more songs in the database to show");
        } else {
            if (previousSong != null) {
                song = previousSong;
                showSong();
            } else if (nextSong != null) {
                song = nextSong;
                showSong();
            }
        }
    }

    private void showSong() {
        play.setDisable(false);
        puase.setDisable(true);
        if (player != null) {
            player.stop();
        }
        String img = song.getImage();
        Image songImage = new Image("file:src/main/resources/assignment/songs/images/" + img);
        image.setImage(songImage);
        title.setText(song.getDataKey().getSongName());
        about.setText(song.getAbout());
    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/main/resources/assignment/songs/images/UMIcon.png"));
            stage.setTitle("Dictionary Exception");
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    public void getSize() {
        switch (this.size.getValue().toString()) {
            case "Small":
                this.songSize = 1;
                break;
            case "Medium":
                this.songSize = 2;
                break;
            case "Large":
                this.songSize = 3;
                break;
            default:
                break;
        }
    }

    public void first() {
        try {
            song = database.smallest();
            showSong();
        } catch (DictionaryException e) {
            displayAlert("No songs in the database.");
        }
    }

    public void last() {
        try {
            song = database.largest();
            showSong();
        } catch (DictionaryException e) {
            displayAlert("No songs in the database.");
        }
    }

    public void next() {
        try {
            song = database.successor(song.getDataKey());
            showSong();
        } catch (DictionaryException e) {
            displayAlert("No next song available.");
        }
    }

    public void previous() {
        try {
            song = database.predecessor(song.getDataKey());
            showSong();
        } catch (DictionaryException e) {
            displayAlert("No previous song available.");
        }
    }

    public void play() {
        String filename = "src/main/resources/assignment/songs/sounds/" + song.getSound();
        media = new Media(new File(filename).toURI().toString());
        player = new MediaPlayer(media);
        play.setDisable(true);
        puase.setDisable(false);
        player.play();
    }

    public void puase() {
        play.setDisable(false);
        puase.setDisable(true);
        if (player != null) {
            player.stop();
        }
    }

    public void loadDictionary() {
        Scanner input;
        int line = 0;
        try {
            String songName = "";
            String description;
            int size = 0;
            input = new Scanner(new File("SongsDatabase.txt"));
            while (input.hasNext()) // read until  end of file
            {
                String data = input.nextLine();
                switch (line % 3) {
                    case 0:
                        size = Integer.parseInt(data);
                        break;
                    case 1:
                        songName = data;
                        break;
                    default:
                        description = data;
                        database.insert(new SongRecord(new DataKey(songName, size), description, songName + ".mp3", songName + ".jpg"));
                        break;
                }
                line++;
            }
        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: SongsDatabase.txt");
            System.out.println(e.getMessage());
        } catch (DictionaryException ex) {
            Logger.getLogger(SongsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.SongPortal.setVisible(true);
        this.first();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        database = new OrderedDictionary();
        size.setItems(FXCollections.observableArrayList(
                "Small", "Medium", "Large"
        ));
        size.setValue("Small");
    }

}
