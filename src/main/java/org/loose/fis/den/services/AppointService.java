package org.loose.fis.den.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.den.model.Appoint;


public class AppointService {
    private static ObjectRepository<Appoint> appointObjectRepository;

    public static ObjectRepository<Appoint> getServicesRepository() {
        return appointObjectRepository;
    }

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("appointments.db").toFile())
                .openOrCreate("test", "test");

        appointObjectRepository = database.getRepository(Appoint.class);
    }

    public static void deleteAppointAsAPacient(String username,String operation){
        for (Appoint op : appointObjectRepository.find()){
            if (username.equals(op.getPacientname())&&operation.equals(op.appointstring())) {
                appointObjectRepository.remove(op);
            }
        }
    }
}
