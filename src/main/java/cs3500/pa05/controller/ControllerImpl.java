package cs3500.pa05.controller;

import static cs3500.pa05.model.DayEnum.fromString;

import cs3500.pa05.model.BujoFileReader;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventImpl;
import cs3500.pa05.model.Model;
import cs3500.pa05.model.ModelImpl;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskImpl;
import cs3500.pa05.view.BujoWeekView;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Implementation of Controller Interface
 */
public class ControllerImpl implements Controller {
  Model model;
  @FXML
  private Label nameOfWeek;
  @FXML
  private Button createNew;
  @FXML
  private Button openExisting;
  @FXML
  private Button saveFile;
  @FXML
  private Button addTask;
  @FXML
  private Button addEvent;
  @FXML
  private VBox monday;
  @FXML
  private VBox tuesday;
  @FXML
  private VBox wednesday;
  @FXML
  private VBox thursday;
  @FXML
  private VBox friday;
  @FXML
  private VBox saturday;
  @FXML
  private VBox sunday;
  @FXML
  private Button addNote;
  @FXML
  private VBox taskQueue;
  @FXML
  private VBox notes;
  @FXML
  private Button weekOverview;
  @FXML
  private MenuItem save;
  @FXML
  private MenuItem open;
  @FXML
  private MenuItem newWeek;
  @FXML
  private MenuItem newEvent;
  @FXML
  private MenuItem newTask;

  private final List<Day> dayInfo;

  private final Stage stage;
  private final HostServices services;
  private String fileName;
  private final List<String> notesList;

  /**
   * Constructor
   *
   * @param stage    stage this BulletJournal will be on
   * @param services host services
   */
  public ControllerImpl(Stage stage, HostServices services) {
    this.stage = stage;
    this.services = services;
    this.dayInfo = new ArrayList<>();
    this.fileName = "bulletJournal.bujo"; // will be overwritten with file path if opening existing
    this.notesList = new ArrayList<>();
  }

  @Override
  public void run() {
    createNew.setOnAction(e -> handleCreateNew());
    openExisting.setOnAction(e -> handleOpenExisting());
  }

  @Override
  public void startBujoWeek(String weekName, int maxEvents, int maxTasks,
                            List<Day> days, String startDay, List<String> notesDisplay) {
    BujoWeekView view = new BujoWeekView(this, startDay);
    stage.setScene(view.load());
    nameOfWeek.setText(weekName);
    saveFile.setOnAction(e -> handleSaveFile());
    newWeek.setOnAction(e -> handleCreateNew());
    open.setOnAction(e -> handleOpenExisting());
    addTask.setOnAction(e -> addTask());
    newTask.setOnAction(e -> addTask());
    addEvent.setOnAction(e -> addEvent());
    newEvent.setOnAction(e -> addEvent());
    addNote.setOnAction(e -> addToNotes());
    weekOverview.setOnAction(e -> displayWeekOverview());
    stage.setTitle(weekName);
    this.model = new ModelImpl(weekName, maxEvents, maxTasks,
        dayInfo, DayEnum.fromString(startDay), notesDisplay);
    stage.show();

    // things to do only when file is already created
    if (!days.isEmpty()) {
      for (Day d : days) {
        dayInfo.add(d);
        for (Task t : d.getTasks()) {
          addTaskToDay(d.dayString(), t.getName(), t.getDescription());
          taskQueue.getChildren().add(createTaskQueueVbox(t.getName()));
          if (t.isComplete()) {
            updateIsCompletedSelection(t.getName(), true);
          }
        }
        for (Event e : d.getEvents()) {
          addEventToDay(d.dayString(), e.getName(), e.getDescription(),
              e.getStartTime(), e.getDuration());
        }
      }
    }

    // making the dayInfo list have an instance of each day
    for (DayEnum de : DayEnum.values()) {
      boolean found = false;
      for (Day d : dayInfo) {
        if (d.isDay(de)) {
          found = true;
        }
      }
      if (!found) {
        dayInfo.add(new Day(de, new ArrayList<>(), new ArrayList<>()));
      }
    }
    for (String n : notesDisplay) {
      Label noteToBeAdded = new Label(n);
      notes.getChildren().add(noteToBeAdded);
      notesList.add(n);
    }
  }


  @Override
  public void handleCreateNew() {
    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label popupLabel = new Label("Create Week!");
    popupLabel.setTextFill(Color.WHITE);
    TextField weekNameField = new TextField("Enter week name");
    TextField maxEventsField = new TextField("Max number of events");
    TextField maxTasksField = new TextField("Max number of tasks");
    Label startDayLabel = new Label("Choose which day you want the week to start with:");
    ComboBox<String> dropdown = new ComboBox<>();
    dropdown.getItems().addAll("Monday", "Sunday");
    Button submitButton = new Button("Submit");
    startDayLabel.setTextFill(Color.WHITE);
    Popup popup = new Popup();

    submitButton.setOnAction(e -> {
      String weekName = weekNameField.getText();
      String maxEventsText = maxEventsField.getText();
      String maxTasksText = maxTasksField.getText();
      String startDay = dropdown.getSelectionModel().getSelectedItem();

      if (startDay == null || startDay.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please choose a start day!");
        alert.showAndWait();
      } else {
        try {
          int maxEvents = Integer.parseInt(maxEventsText);
          int maxTasks = Integer.parseInt(maxTasksText);
          startBujoWeek(weekName, maxEvents, maxTasks, new ArrayList<>(),
              startDay, new ArrayList<>());
          popup.hide();
        } catch (NumberFormatException ex) {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Input Error");
          alert.setHeaderText(null);
          alert.setContentText("Max number of events and max number of tasks must be integers!");
          alert.showAndWait();
          maxEventsField.setText("");
          maxTasksField.setText("");
          maxEventsField.requestFocus();
        }
      }
    });

    popupContent.getChildren()
        .addAll(popupLabel, weekNameField, maxEventsField, maxTasksField, startDayLabel, dropdown,
            submitButton);
    popupContent.setAlignment(Pos.CENTER);
    popup.getContent().add(popupContent);

    popup.show(stage);
  }


  @Override
  public void handleOpenExisting() {
    Popup popup = new Popup();

    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label popupLabel = new Label("Open .bujo File!");
    popupLabel.setTextFill(Color.WHITE);
    TextField fileNameField = new TextField("Enter path to file");
    Button submitButton = new Button("Submit");

    submitButton.setOnAction(e -> {
      fileName = fileNameField.getText();
      boolean workingPath = true;
      try {
        Paths.get(fileName);
      } catch (InvalidPathException ex) {
        workingPath = false;
      }

      if (fileName == null || !fileName.endsWith(".bujo") || !workingPath) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a correct .bujo file path!");
        alert.showAndWait();
      } else {
        BujoFileReader bfr = new BujoFileReader(fileName, this);
        bfr.createSceneFromFile();
        popup.hide();
      }
    });

    popupContent.getChildren()
        .addAll(popupLabel, fileNameField, submitButton);
    popupContent.setAlignment(Pos.CENTER);
    popup.getContent().add(popupContent);

    popup.show(stage);
  }

  @Override
  public void addTask() {
    final String[] taskName = {""};
    final String[] description = {""};
    final String[] selectedDay = {""};

    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label popupLabel = new Label("Add task");
    popupLabel.setTextFill(Color.WHITE);
    TextField taskNameField = new TextField("Enter task name");
    TextField descriptionField = new TextField("Enter Description");
    ComboBox<String> dropdown = new ComboBox<>();
    dropdown.getItems()
        .addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    Button submitButton = new Button("Submit");

    Popup popup = new Popup();
    submitButton.setOnAction(e -> {
      taskName[0] = taskNameField.getText();
      description[0] = descriptionField.getText();
      selectedDay[0] = dropdown.getSelectionModel().getSelectedItem();

      if (taskName[0].isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a task name!");
        alert.showAndWait();
      } else if (selectedDay[0] == null || selectedDay[0].isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please choose a day!");
        alert.showAndWait();
      } else if (!this.model.getCurrentWeek()
          .checkIfValidNumTasksPerDay(1, fromString(selectedDay[0]))) {
        throwCommittmentWarning("task");
      } else {
        // adding task to day info for save info
        Task newTask = new TaskImpl(taskName[0], description[0],
            DayEnum.fromString(selectedDay[0]));
        for (Day d : dayInfo) {
          if (d.isDay(DayEnum.fromString(selectedDay[0]))) {
            d.addTask(newTask);
          }
        }

        // displaying task
        addTaskToDay(selectedDay[0], taskName[0], description[0]);
        taskQueue.getChildren().add(createTaskQueueVbox(taskName[0]));
        popup.hide();
      }
    });

    popupContent.getChildren()
        .addAll(popupLabel, taskNameField, descriptionField, dropdown, submitButton);
    popup.getContent().add(popupContent);
    popup.show(stage);
  }

  /**
   * Helper Method that adds a task to the selected day in both the GUI and the model
   *
   * @param selectedDay day that the user selected
   * @param taskName    name of the task
   * @param description description of the task
   */
  private void addTaskToDay(String selectedDay, String taskName, String description) {
    Day[] days = model.getCurrentWeek().getDays();
    if (selectedDay != null && selectedDay.equalsIgnoreCase("Monday")) {
      Task task = new TaskImpl(taskName, description, DayEnum.MONDAY);
      monday.getChildren().add(createTaskVbox(task));
      days[0].addTask(task);
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Tuesday")) {
      Task task = new TaskImpl(taskName, description, DayEnum.TUESDAY);
      tuesday.getChildren().add(createTaskVbox(task));
      days[1].addTask(task);
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Wednesday")) {
      Task task = new TaskImpl(taskName, description, DayEnum.WEDNESDAY);
      wednesday.getChildren().add(createTaskVbox(task));
      days[2].addTask(task);
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Thursday")) {
      Task task = new TaskImpl(taskName, description, DayEnum.THURSDAY);
      thursday.getChildren().add(createTaskVbox(task));
      days[3].addTask(task);
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Friday")) {
      Task task = new TaskImpl(taskName, description, DayEnum.FRIDAY);
      friday.getChildren().add(createTaskVbox(task));
      days[4].addTask(task);
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Saturday")) {
      Task task = new TaskImpl(taskName, description, DayEnum.SATURDAY);
      saturday.getChildren().add(createTaskVbox(task));
      days[5].addTask(task);
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Sunday")) {
      Task task = new TaskImpl(taskName, description, DayEnum.SUNDAY);
      sunday.getChildren().add(createTaskVbox(task));
      days[6].addTask(task);
    }
  }

  /**
   * Creates the actual Task VBox to be appended onto the GUI
   *
   * @param task Task object
   * @return VBox to be appended
   */
  private VBox createTaskVbox(Task task) {
    VBox taskBox = new VBox();
    Label taskLabel = new Label(task.getName());
    Label descriptionLabel = new Label("Description: " + task.getDescription());
    CheckBox checkBox = new CheckBox("Done");
    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {

      // When the checkbox in the task queue is selected/deselected,
      // find the corresponding checkbox in the day and update its selection.
      if (newValue) {
        task.setAsComplete();
      } else {
        task.setAsIncomplete();
      }
      updateIsCompletedSelection(task.getName(), newValue);
    });
    Button expandTask = new Button("Expand");

    expandTask.setOnAction(e -> {
      VBox rootContent = new VBox();
      BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
      rootContent.setBackground(new Background(fill));
      Label taskLabelCopy = new Label(taskLabel.getText());
      Label descriptionLabelCopy = new Label(descriptionLabel.getText());
      taskLabelCopy.setTextFill(Color.WHITE);
      descriptionLabelCopy.setTextFill(Color.WHITE);

      List<String> links = getLinks(task.getDescription());
      if (!links.isEmpty()) {
        VBox linksContainer = new VBox();
        for (String link : links) {
          Hyperlink hyperlink = setHyperlink(link);
          linksContainer.getChildren().add(hyperlink);
        }
        rootContent.getChildren().addAll(taskLabelCopy, linksContainer);
      } else {
        rootContent.getChildren().addAll(taskLabelCopy, descriptionLabelCopy);
      }
      Stage informationStage = new Stage();

      Scene scene = new Scene(rootContent, 400, 200);
      informationStage.setScene(scene);
      informationStage.setTitle(task.getDayOfWeek().toString());
      informationStage.show();
    });
    taskBox.getChildren().addAll(taskLabel, descriptionLabel, checkBox, expandTask);
    return taskBox;
  }

  /**
   * Formats the task into a VBox to be placed into the TaskQueue
   *
   * @param taskName name of the task
   * @return task as a VBox
   */
  private VBox createTaskQueueVbox(String taskName) {
    VBox task = new VBox();
    Label taskLabel = new Label(taskName);
    Label completedLabel = new Label("Not completed");
    task.getChildren().addAll(taskLabel, completedLabel);
    return task;
  }

  /**
   * Updates the task's status to completed once the checkbox in the GUI is clicked
   *
   * @param taskName name of the task
   * @param selected whether the task was selected or not
   */
  private void updateIsCompletedSelection(String taskName, boolean selected) {
    List<Node> tasks = taskQueue.getChildren();
    for (Node task : tasks) {
      if (task instanceof VBox taskVbox) {
        if (taskVbox.getChildren().get(0) instanceof Label taskNameLabel) {
          Label isCompletedLabel = (Label) taskVbox.getChildren().get(1);
          if (Objects.equals(taskNameLabel.getText(), taskName)) {
            if (selected) {
              isCompletedLabel.setText("Completed");
            } else {
              isCompletedLabel.setText("Not completed");
            }
          }
        }
      }
    }
  }

  @Override
  public void addEvent() {
    final String[] eventName = {""};
    final String[] description = {""};
    final String[] selectedDay = {""};
    final String[] startTime = {""};
    final String[] duration = {""};

    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label popupLabel = new Label("Add event");
    popupLabel.setTextFill(Color.WHITE);
    TextField eventNameField = new TextField("Enter event name");
    TextField descriptionField = new TextField("Description of Event");
    ComboBox<String> dropdown = new ComboBox<>();
    dropdown.setItems(FXCollections.observableArrayList(
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
    TextField startTimeField = new TextField("Start time in military time");
    TextField durationField = new TextField("Duration in minutes");
    Button submitButton = new Button("Submit");
    Popup popup = new Popup();

    submitButton.setOnAction(e -> {
      eventName[0] = eventNameField.getText();
      description[0] = descriptionField.getText();
      selectedDay[0] = dropdown.getSelectionModel().getSelectedItem();
      startTime[0] = startTimeField.getText();
      duration[0] = durationField.getText();

      if (eventName[0].isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter an event name!");
        alert.showAndWait();
      } else if (selectedDay[0] == null || selectedDay[0].isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please choose a day!");
        alert.showAndWait();
      } else if (startTime[0].isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a start time!");
        alert.showAndWait();
      } else if (duration[0].isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a duration!");
        alert.showAndWait();
      } else if (!this.model.getCurrentWeek()
          .checkIfValidNumEventsPerDay(1, fromString(selectedDay[0]))) {
        throwCommittmentWarning("event");
      } else {
        try {
          int durationValue = Integer.parseInt(duration[0]);

          // adding event to day info for save info
          Event newEvent = new EventImpl(eventName[0], description[0],
              DayEnum.fromString(selectedDay[0]), startTime[0], duration[0]);
          for (Day d : dayInfo) {
            if (d.isDay(DayEnum.fromString(selectedDay[0]))) {
              d.addEvent(newEvent);
            }
          }
          popup.hide();
          addEventToDay(selectedDay[0], eventName[0], description[0], startTime[0], duration[0]);
        } catch (NumberFormatException ex) {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Input Error");
          alert.setHeaderText(null);
          alert.setContentText("Duration must be an integer!");
          alert.showAndWait();
        }
      }
    });

    popupContent.getChildren()
        .addAll(popupLabel, eventNameField, descriptionField, dropdown, startTimeField,
            durationField, submitButton);

    popup.getContent().add(popupContent);

    popup.show(stage);
  }

  /**
   * Adds event to the GUI and model
   *
   * @param selectedDay day the user selected
   * @param eventName   name of the event
   * @param description description of the event
   * @param startTime   starting time of the event
   * @param duration    duration of the event
   */
  private void addEventToDay(String selectedDay, String eventName, String description,
                             String startTime, String duration) {
    Day[] days = model.getCurrentWeek().getDays();
    if (selectedDay != null && selectedDay.equalsIgnoreCase("Monday")) {
      monday.getChildren()
          .add(createEventVbox(eventName, description, startTime, duration, selectedDay));
      days[0].addEvent(new EventImpl(eventName, description, DayEnum.MONDAY, startTime, duration));
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Tuesday")) {
      tuesday.getChildren()
          .add(createEventVbox(eventName, description, startTime, duration, selectedDay));
      days[1].addEvent(new EventImpl(eventName, description, DayEnum.TUESDAY, startTime, duration));
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Wednesday")) {
      wednesday.getChildren()
          .add(createEventVbox(eventName, description, startTime, duration, selectedDay));
      days[2].addEvent(
          new EventImpl(eventName, description, DayEnum.WEDNESDAY, startTime, duration));
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Thursday")) {
      thursday.getChildren()
          .add(createEventVbox(eventName, description, startTime, duration, selectedDay));
      days[3].addEvent(
          new EventImpl(eventName, description, DayEnum.THURSDAY, startTime, duration));
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Friday")) {
      friday.getChildren()
          .add(createEventVbox(eventName, description, startTime, duration, selectedDay));
      days[4].addEvent(new EventImpl(eventName, description, DayEnum.FRIDAY, startTime, duration));
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Saturday")) {
      saturday.getChildren()
          .add(createEventVbox(eventName, description, startTime, duration, selectedDay));
      days[5].addEvent(
          new EventImpl(eventName, description, DayEnum.SATURDAY, startTime, duration));
    } else if (selectedDay != null && selectedDay.equalsIgnoreCase("Sunday")) {
      sunday.getChildren()
          .add(createEventVbox(eventName, description, startTime, duration, selectedDay));
      days[6].addEvent(new EventImpl(eventName, description, DayEnum.SUNDAY, startTime, duration));
    }
  }

  /**
   * Creates the VBox for the Event
   *
   * @param eventName   name of the event
   * @param description description of the event
   * @param startTime   start time of the event
   * @param duration    duration of the event
   * @param dayOfWeek   day of the week the user selected
   * @return Vbox of the event with above information
   */
  private VBox createEventVbox(String eventName, String description,
                               String startTime, String duration, String dayOfWeek) {
    VBox event = new VBox();
    Label eventLabel = new Label(eventName);
    Label descriptionLabel = new Label("Description: " + description);
    Label startTimeLabel = new Label("Start: " + startTime);
    Label durationLabel = new Label(duration + " minutes");
    Button expandEvent = new Button("Expand");

    expandEvent.setOnAction(e -> {
      VBox rootContent = new VBox();
      BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
      rootContent.setBackground(new Background(fill));
      Label eventLabelCopy = new Label(eventLabel.getText());
      Label descriptionLabelCopy = new Label(descriptionLabel.getText());
      Label startTimeLabelCopy = new Label(startTimeLabel.getText());
      eventLabelCopy.setTextFill(Color.WHITE);
      descriptionLabelCopy.setTextFill(Color.WHITE);
      startTimeLabelCopy.setTextFill(Color.WHITE);
      Label durationLabelCopy = new Label("Duration: " + durationLabel.getText());
      durationLabelCopy.setTextFill(Color.WHITE);

      List<String> links = getLinks(description);
      if (!links.isEmpty()) {
        VBox linksContainer = new VBox();
        for (String link : links) {
          Hyperlink hyperlink = setHyperlink(link);
          linksContainer.getChildren().add(hyperlink);
        }
        rootContent.getChildren()
            .addAll(eventLabelCopy, linksContainer, startTimeLabelCopy, durationLabelCopy);
      } else {
        rootContent.getChildren()
            .addAll(eventLabelCopy, descriptionLabelCopy, startTimeLabelCopy, durationLabelCopy);
      }
      Stage informationStage = new Stage();

      Scene scene = new Scene(rootContent, 400, 200);
      informationStage.setScene(scene);
      informationStage.setTitle(dayOfWeek);
      informationStage.show();
    });

    event.getChildren()
        .addAll(eventLabel, descriptionLabel, startTimeLabel, durationLabel, expandEvent);
    return event;
  }

  @Override
  public List<String> getLinks(String description) {
    List<String> links = new ArrayList<>();

    String pattern = "https?://\\S+";
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(description);

    while (matcher.find()) {
      links.add(matcher.group());
    }

    return links;
  }

  @Override
  public Hyperlink setHyperlink(String linkText) {
    Hyperlink hyperlink = new Hyperlink(linkText);
    hyperlink.setOnAction(e -> {
      services.showDocument(linkText);
    });
    return hyperlink;
  }

  @Override
  public void addToNotes() {
    Popup popup = new Popup();
    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label popupLabel = new Label("Enter note here");
    popupLabel.setTextFill(Color.WHITE);
    TextField noteEntry = new TextField("Start note");
    Button submitButton = new Button("Submit");

    submitButton.setOnAction(e -> {
      notesList.add(noteEntry.getText());
      Label noteToBeAdded = new Label(noteEntry.getText());
      notes.getChildren().add(noteToBeAdded);
      popup.hide();
    });

    popupContent.getChildren().addAll(popupLabel, noteEntry, submitButton);
    popupContent.setAlignment(Pos.CENTER);
    popup.getContent().add(popupContent);
    Stage primaryStage = (Stage) addNote.getScene().getWindow();
    popup.show(primaryStage);
  }

  @Override
  public void displayWeekOverview() {
    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label popupLabel = new Label("Here is your Weekly Overview!");
    popupLabel.setTextFill(Color.WHITE);
    ArrayList<Integer> content = model.getCurrentWeek().getOverview();
    Label totalEvents = new Label("Total Number of Events: " + content.get(0));
    Label totalTasks = new Label("Total Number of Tasks: " + content.get(1));
    totalEvents.setTextFill(Color.WHITE);
    totalTasks.setTextFill(Color.WHITE);
    String taskCompleteString =
        "Percentage Of Tasks Complete: " + content.get(2) + "% or " + content.get(3) + "/"
            + content.get(1);
    Label percentageOfTasksComplete = new Label(taskCompleteString);
    percentageOfTasksComplete.setTextFill(Color.WHITE);
    Button exitButton = new Button("Exit Overview");
    Popup popup = new Popup();
    exitButton.setOnAction(e -> {
      popup.hide();
    });


    popupContent.getChildren()
        .addAll(popupLabel, totalEvents, totalTasks, percentageOfTasksComplete, exitButton);
    popupContent.setAlignment(Pos.CENTER);
    popup.getContent().add(popupContent);
    Stage primaryStage = (Stage) addNote.getScene().getWindow();
    popup.show(primaryStage);
  }


  @Override
  public void handleSaveFile() {
    // write to file
    model.saveFile(fileName);

    //pop up to let them know save was successful
    Popup popup = new Popup();
    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label popupLabel = new Label("Save Successful!");
    popupLabel.setTextFill(Color.WHITE);
    Button exitButton = new Button("Exit");
    exitButton.setOnAction(f -> {
      popup.hide();
    });

    popupContent.getChildren()
        .addAll(popupLabel, exitButton);
    popupContent.setAlignment(Pos.CENTER);
    popup.getContent().add(popupContent);
    Stage primaryStage = (Stage) addNote.getScene().getWindow();
    popup.show(primaryStage);
  }

  /**
   * Throws warning if a user tries to add more tasks or events than the max (that they specified
   * previously)
   *
   * @param eventOrTask boolean on whether it's throwing for adding too many events, or too many
   *                    tasks
   */
  public void throwCommittmentWarning(String eventOrTask) {
    Popup popup = new Popup();
    VBox popupContent = new VBox();
    BackgroundFill fill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    popupContent.setBackground(new Background(fill));
    Label warning = new Label("WARNING: Too many " + eventOrTask + " for one of your days");
    warning.setTextFill(Color.WHITE);
    Button exitButton = new Button("Exit Overview");
    exitButton.setOnAction(e -> {
      popup.hide();
    });

    popupContent.getChildren().addAll(warning, exitButton);
    popup.getContent().add(popupContent);
    Stage primaryStage = (Stage) addNote.getScene().getWindow();
    popup.show(primaryStage);
  }
}
