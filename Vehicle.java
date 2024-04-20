import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class Vehicle {
    @Override
    public int hashCode(){
        String str = "";
        str += (int) type.charAt(0) + "" + (int) type.charAt(2);
        String category = properties.getVehicleMetaData().getTransport().getType();
        switch (category){
            case "bus" : str += "1";
            case "tramway": str += "2";
            default: str += "3";
        }
        str += properties.getVehicleMetaData().getTransport().getName().charAt(0);
        int ret = Integer.parseInt(str);
        ret += (int) properties.getVehicleMetaData().getId().charAt(4);
        ret += (int) properties.getVehicleMetaData().getId().charAt(6);
        ret += (int) properties.getVehicleMetaData().getId().charAt(8);
        ret += (int) properties.getVehicleMetaData().getId().charAt(10);
        ret += (int) properties.getVehicleMetaData().getId().charAt(properties.getVehicleMetaData().getId().length() - 1);
        ret += (int) properties.getVehicleMetaData().getId().charAt(properties.getVehicleMetaData().getId().length() - 5);
        ret += (int) properties.getVehicleMetaData().getId().charAt(properties.getVehicleMetaData().getId().length() - 3);
        return ret;
    }

    public String toString(){
        return properties.getVehicleMetaData().getTransport().getType() + " " + properties.getVehicleMetaData().getTransport().getName() +
                " (" + hashCode() + ")";
    }
    private String type;
    private Properties properties;
    private List<Feature> features;
}
