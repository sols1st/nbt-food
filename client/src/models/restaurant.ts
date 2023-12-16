interface Restaurant {
    id?: number
    name?: string
    pic?: string
    userId?: number
    description?: string
    locationId?: number
    locationName?: string
}

interface Location {
    id?: number
    name?: string
    description?: string
}

interface RestaurantLocation {
    location?: Location
    restaurantList?: Restaurant[]
}

export type { Restaurant, Location, RestaurantLocation }