package org.loose.fis.den.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.den.model.Appoint;

import static org.loose.fis.den.services.FileAppointService.getPathToFile;

public class AppointService {
    private static ObjectRepository<Appoint> appointObjectRepository;

    public static ObjectRepository<Appoint> getAppointsRepository() {
        return appointObjectRepository;
    }

    public static void initDatabase() {
        Nitrite db = Nitrite.builder()
                .filePath(FileAppointService.getPathToFile("appointments.db").toFile())
                .openOrCreate("test", "test");

        appointObjectRepository = db.getRepository(Appoint.class);
    }

    public static void deleteAppointAsAPacient(String username,String operation){
        for (Appoint op : appointObjectRepository.find()){
            if (username.equals(op.getPacientname())&&operation.equals(op.appointstring())) {
                appointObjectRepository.remove(op);
            }
        }
    }
}