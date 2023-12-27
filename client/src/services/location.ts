import Axios from "@/utils/axios";
import { Location } from "@/models/location";
import Toast from "@/utils/toast";

const LocationService = {
  async listLocation() {
    return (await Axios("/location/list", null, "GET")) as Location[];
  },
  async addLocation(l: Location) {
    const res = (await Axios("/location/add", l, "post")) as string;
    Toast(res);
    return res;
  },
  async removeLocation(l: Location) {
    const res = (await Axios("/location/delete/" + l.id,null,"delete")) as string;
    Toast(res);
    return res;
  },
  async updateLocation(r: Location) {
    const res = (await Axios("/location/update", r, "put")) as string;
    Toast(res);
    return res;
  },
};

export default LocationService;
