# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)


Welcome to JavaJournal!
- Week View - view your week in a beautiful and interactive GUI
- Event and Task Creation - Keep track of tasks and events by adding them to your weekly view. Expand these tasks to see them in a separate window as well as open saved links
- Error handling is built-in, so no need to worry about the program randomly breaking because of your input.
- Save week journals to your laptop and load them any time you open the app.
- Features a built-in task queue that allows you to scroll through and keep track of all of your tasks.
- Fully integrated menu bar and support for shortcuts when adding tasks and events, as well as saving and opening new files.
- Want to take notes? We have you covered with our Quotes and Notes feature that allows you to jot down notes and save them to the weekly view.
- Want to see your week in review? Check out your weekly overview which displays pertinent stats (like how percentage of completed tasks) for you to see.
- We know some of you think the week starts on Sunday, so we added the ability to select Sunday as the starting day in the week (as well as Monday of course).
- Finally, you can also focus in on one specific task or event by expanding it to a new window

![Screenshot 2023-06-21 at 11.55.52 PM.png](Screenshot%202023-06-21%20at%2011.55.52%20PM.png)
SOLID Principles
- S - Single Responsibility Principles - The BujoFileReader class follows the Single Responsibility Principle (S) by focusing on a single responsibility: reading a Bujo file. This principle states that a class should have only one reason to change, and by adhering to this principle, the codebase becomes more modular and maintainable. In the case of BujoFileReader, its main task is to read the contents of a Bujo file, parse it using Jackson's ObjectMapper, and extract the necessary information to create a scene or view. By keeping the responsibility of file reading isolated in this class, it becomes easier to modify or extend the file reading functionality without affecting other parts of the system. Similarly, the BujoFileWriter class can be expected to have the single responsibility of writing Bujo files, ensuring that each class has a clear and distinct purpose within the overall system design.
- O - Open Closed Principle - The Open-Closed Principle (O) is reflected in the design of the codebase by utilizing interfaces for most of the classes. This design choice allows for easy extensibility of the system. For example, the Action interface serves as a common interface for both the Event and Task interfaces, which are then implemented by concrete classes. By defining interfaces, the codebase remains open for extension, as new types of actions can be easily added by implementing the Action interface. This approach promotes code reusability and allows for the addition of new functionality without modifying existing code, thus adhering to the Open-Closed Principle.
- L - Liskov Substitution Principle - The Liskov Substitution Principle (LSP) played a significant role in our codebase as we aimed for robustness and flexibility. To adhere to LSP, we designed our system to utilize interface references extensively. For instance, we created numerous references to the Task interface, which allowed us to work with different implementations such as TaskImpl objects interchangeably. This approach was replicated across various other interfaces within our system, including Event and EventImpl, among others. By embracing LSP and employing interface references, we achieved a high level of modularity and extensibility, enabling us to seamlessly incorporate different implementations while maintaining the overall integrity and consistency of our codebase.
- I - Interface Segregation Principle -
  The Interface Segregation Principle (ISP) was a guiding principle in our development process, ensuring that our interfaces remained cohesive and well-defined. To adhere to ISP, we diligently ensured that every class implementing an interface would utilize and implement all the methods defined within that interface. This practice allowed us to avoid the pitfall of having bloated interfaces that encompassed more functionality than necessary for specific classes. By adhering to ISP, we achieved a more granular and focused approach, where each class only implemented the methods it needed, leading to improved code readability, maintainability, and a stronger adherence to the Single Responsibility Principle.
- D - Dependency Inversion Principle - The code provided demonstrates the application of the Dependency Inversion Principle in the initialization of the BujoWeekView class. The principle states that high-level modules should not depend on low-level modules but should instead depend on abstractions. In this case, the BujoWeekView class depends on the Controller interface rather than a specific implementation of the controller. This allows for flexibility and modularity in the codebase, as different implementations of the Controller interface can be easily substituted without affecting the functionality of the BujoWeekView class. By relying on abstractions rather than concrete implementations, the Dependency Inversion Principle promotes loose coupling and facilitates easier maintenance and extensibility of the code.

Extendable Feature - Progress Bar

To implement the feature of displaying a daily progress bar and task count on the Week view using JavaFX, we can modify the existing BujoWeekView class to include additional JavaFX components for visualization. This involves enhancing the graphical user interface (GUI) layout by adding ProgressBar and Label controls to represent the completion status and remaining tasks for each day. We would update the Controller interface to include methods for retrieving the necessary data, such as completion status and remaining tasks for each day, and implement these methods in the concrete ControllerImpl class.

Within the BujoWeekView class, we would leverage JavaFX's scene graph structure to create and organize the GUI components. We can use a GridPane layout to arrange the progress bars and labels in a grid-like fashion, mapping them to the corresponding days of the week. By binding the properties of the progress bars and labels to the data retrieved from the controller, the visual representation will dynamically update as the underlying data changes.

The ControllerImpl class would be responsible for calculating the completion status and remaining tasks for each day based on the underlying data model. It would provide methods for retrieving this information, which will be used by the BujoWeekView class to update the progress bars and labels accordingly.

Testing would be conducted to verify the accuracy and functionality of the extended feature, including test cases for various task completion statuses. This implementation provides users with a visually appealing and informative way to track their daily task progress and remaining tasks, enhancing the application's usability and effectiveness. The design allows for easy maintenance and future extensions or modifications to the feature as needed, leveraging the power and flexibility of JavaFX's rich GUI capabilities.


