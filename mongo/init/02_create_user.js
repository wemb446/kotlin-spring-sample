db = db.getSiblingDB("admin");
db.auth("root", "pass1234");
db.createUser({
    user: "appuser",
    pwd: "pass1234",
    roles: [
        {
            role: "readWrite",
            db: "SAMPLE_DB",
        },
    ],
});
