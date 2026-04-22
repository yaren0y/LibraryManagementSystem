package org.example.command;

public class CommandInvoker {
    private Command lastCommand;

    public void executeCommand(Command command) {
        this.lastCommand = command;
        command.execute();
    }

    public void undoLastCommand() {
        if (lastCommand != null) {
            lastCommand.undo();
            lastCommand = null; // Sadece son işlemi geri alabilir
        } else {
            System.out.println("No command to undo.");
        }
    }
}
