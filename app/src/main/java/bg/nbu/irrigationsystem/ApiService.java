package bg.nbu.irrigationsystem;

import com.google.gson.JsonObject;

import java.util.List;

import bg.nbu.irrigationsystem.model.PlantModel;
import bg.nbu.irrigationsystem.model.PlantTypeModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/auth/login")
    Call<JsonObject> login(@Body JsonObject credentials);

    @POST("api/auth/register")
    Call<JsonObject> register(@Body JsonObject userInfo);

    @GET("api/plant/types")
    Call<List<PlantTypeModel>> getPlantTypes();

    @GET("api/plant/plants")
    Call<List<PlantModel>> getPlants();

    @POST("api/plant/irrigate/{deviceId}/{relayId}/{irrigationDuration}")
    Call<String> irrigate(
            @Path("deviceId") int deviceId,
            @Path("relayId") int relayId,
            @Path("irrigationDuration") int irrigationDuration
    );

    @POST("api/plant/create")
    Call<String> createPlant(@Body JsonObject plantInfo);
}
