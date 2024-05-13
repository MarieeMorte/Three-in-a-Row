# Java language features used in the project

### ActionListener interface

The implementation of the ActionListener interface in the Gameplay class allows to handle actions that occur with
interface elements, such as buttons.

### Event handling

Adding action listeners to buttons and processing them in the actionPerformed method allows to respond to user
actions, such as button clicks.

### Lambda expressions

Lambda expressions simplify the processing of various actions depending on the command.

### Layout Management

Using layout managers (BorderLayout, FlowLayout, GridLayout) allows to control the placement of components on the form.

### Multithreading

Using multithreading with SwingWorker allows to perform lengthy operations in the background without blocking the
main interface thread.

### Static variables

Using the static keyword to declare the static variables allows them to be shared by all instances of their classes.

### Swing GUI

Using the Swing library allows to create a graphical interface (GUI) of the application. I create various
components such as buttons (JButton), panels (JPanel), labels (JLabel) and a frame (JFrame) to build a game interface.

### SwingWorker

The nested SwingWorker game class inherits from SwingWorker to perform background work in the graphical user interface,
allowing not to block the main thread.

### Switch expression

Using a switch expression allows to conveniently handle various options for actions.

### Working with resources

Using the getResource method allows to upload images and use them using setIconImage.
