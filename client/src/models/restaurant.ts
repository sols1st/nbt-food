interface Restaurant {
    id?: number
    name?: string
    pic?: string
    userId?: number
    description?: string
    locationId?: number
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