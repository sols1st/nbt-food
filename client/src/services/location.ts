import Axios from "@/utils/axios";
import { Location } from "@/models/location";

const LocationService = {
  async listLocation() {
    return (await Axios("/location/list", null, "GET",false)) as Location[];
  },
  async addLocation(l: Location) {
    return (await Axios("/location/add", l, "post")) as string;
  },
  async removeLocation(l: Location) {
    return (await Axios("/location/delete/" + l.id, null, "delete")) as string;
  },
  async updateLocation(r: Location) {
    return (await Axios("/location/update", r, "put")) as string;
  },
};

export default LocationService;
