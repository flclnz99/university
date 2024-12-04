lista1 = [1, 2, 3, 4]
lista2 = [1, 4, 5, 6]
value1 = 0
value2 = 0


def intersezione(lista1, lista2, value1, value2):
    for value1 in lista1 and value2 in lista2:
        print(value1)
        print(value2)
        if lista1[value1] == lista2[value2]:
            print("ciao")


intersezione(lista1, lista2, value1, value2)
