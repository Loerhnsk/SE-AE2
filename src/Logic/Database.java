package Logic;

import Entity.AssignedRequirement;
import Entity.Teacher;
import Entity.TeachingRequirement;

import java.util.List;

public class Database {

    private DataWriter dataWriter;
    private DataReader dataReader;

    // Constructors
    public Database(DataReader reader, DataWriter dataWriter) {
        this.dataReader = reader;
        this.dataWriter = dataWriter;
        // Initialize data lists or perform other necessary setup
    }

    public Database() {
        this.dataReader = new TxtFileDataReader();
        this.dataWriter = new TxtFileDataWriter();
        // Initialize data lists or perform other necessary setup
    }

    // Getters and Setters
    public DataWriter getDataWriter() {
        return dataWriter;
    }

    public void setDataWriter(DataWriter dataWriter) {
        this.dataWriter = dataWriter;
    }

    public DataReader getDataReader() {
        return dataReader;
    }

    public void setDataReader(DataReader dataReader) {
        this.dataReader = dataReader;
    }
}
