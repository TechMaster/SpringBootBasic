def create_person(data):
    if data['age']['year'] < 18:
        return None