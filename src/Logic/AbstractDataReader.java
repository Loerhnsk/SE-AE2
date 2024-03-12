package Logic;

public abstract class AbstractDataReader implements DataReader {

    public AbstractDataReader() {
    }

    // Public methods
    // Concrete implementations for reading from file are left to subclasses
    protected abstract void readTeachingRequirementsFromFile(String filePath);

    protected abstract void readTeachersFromFile(String filePath);

    protected abstract void readAssignedRequirementsFromFile(String filePath);
}
