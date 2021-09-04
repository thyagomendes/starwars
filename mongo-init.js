db = db.getSiblingDB('starwars');
db.createUser(
  {
    user: 'root',
    pwd: 'root',
    roles: [{ role: 'readWrite', db: 'starwars' }],
  },
);
db.createCollection('planets');
db.planets.insert( { swapi_id: 1, name: 'Tatooine', climate: 'arid', terrain: 'desert'} )
db.planets.insert( { swapi_id: 2, name: 'Alderaan', climate: 'temperate', terrain: 'grasslands, mountains'} )

// db.createCollection( "planets",
//    { validator: { $or:
//       [
//         { id: { $type: "int" } }, 
//         { name: { $type: "string" } },
//         { climate: { $type: "string" } },
//         { terrain: { $type: "string" } }
//       ]
//    }
// } )