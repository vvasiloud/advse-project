resource flight
get /flights :επιστρέφει όλες τις πτήσεις με παραμέτρους: to,from, availableSeats(int), minPrice(int), maxPrice(int)
get /flights/{flightId} :επιστρέφει μία πτήση με συγκρεκριμένο flightId, με παραμέτρους flightId(PathVariable)
get /flights/{flightId}/seats :επιστρέφει όλες τις θέσεις μιας συγκεκριμένης πτήσης με παράμετρο το flightId(PathVariable)
