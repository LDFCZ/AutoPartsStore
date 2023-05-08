from bson.objectid import ObjectId

ids = [str(ObjectId()) for i in range(100)]
print(ids)