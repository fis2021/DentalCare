package org.loose.fis.den.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import org.loose.fis.den.exceptions.EmptyTextException;
import org.loose.fis.den.exceptions.OperationExistsException;
import org.loose.fis.den.model.Operations;
import org.loose.fis.den.model.loggedUser;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class OperationService {
    private static ObjectRepository<Operations> operationObjectRepository;

    public static ObjectRepository<Operations> getOperationRepository() {
        return operationObjectRepository;
    }

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("operations.db").toFile())
                .openOrCreate("test", "test");

        operationObjectRepository = database.getRepository(Operations.class);
    }

    public static void addOp(String username, String operation) throws EmptyTextException, OperationExistsException {
        checkEmptyText(operation);
        checkOperationExists(operation);
        operationObjectRepository.insert(new Operations(username, operation));
    }

    private static void checkEmptyText(String text) throws EmptyTextException {
        if (text.equals(""))
            throw new EmptyTextException();
    }




    public static void deleteOp(String name,String operation){
        Operations aux = new Operations();

        for (Operations op : operationObjectRepository.find()){
            if (name.equals(op.getDoctorname()) && operation.equals(op.getOperation())) {
                aux = op;
            }
        }

        operationObjectRepository.remove(eq("operation",operation),aux);
    }

    private static void checkOperationExists(String operation)  throws OperationExistsException {

        int k=0;

        for (Operations op : operationObjectRepository.find()){
            if (loggedUser.getActualUser().equals(op.getDoctorname()))
                if (operation.equals(op.getOperation()))
                    k++;
        }

        if (k != 1) throw new OperationExistsException();

    }
}