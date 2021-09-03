db = db.getSiblingDB('starwars');
db.createUser(
  {
    user: 'root',
    pwd: 'root',
    roles: [{ role: 'readWrite', db: 'starwars' }],
  },
);
db.createCollection('users');