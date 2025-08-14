import os

from platform import architecture

from sqlite3 import DateFromTicks

import requests

from pprint import pprint

'''

Lista de todos los comandos

Cada uno con su id, titulo, nombre, respuesta, subComandos

Estos subcomandos solo van a ser a un nivel, no a más

Los subcomandos solo tendrán de campo el id, título y nombre

En caso no tenga subcomandos, este campo será un arreglo vacío

Finalmente tendrá un campo que indique en el nivel que estamos(aún no está implementado)



Se supone que este json vendrá desde el back, esta consulta solo se hará al ejecutar el bot, no cuando el usuario realize las consultas

'''



URL = os.getenv("BACKEND_URL", "http://backend:8080/")

ID_ESPECIALIDAD="1"



def cargaInicial():

    res=requests.get(URL+"comandos/listar/"+ID_ESPECIALIDAD)

    return res.json()["data"]



def enviarDatosUsuario(nombreUsuario,idComando,idUsuario):

    params = {'usuario' : nombreUsuario, 'idComando' : idComando, 'idUsuario' : idUsuario}

    print(params)

    res=requests.post(URL+"historial/registrar",json=params)

    return



def listarUsuariosVerificados():

    res=requests.get(URL+"usuarios/listar/"+ID_ESPECIALIDAD)

    resDict={}

    for idUsuario in res.json()["data"]:

        resDict[idUsuario]=True

    return resDict



def enviarToken(idUsuario,nombreUsuario,codigoUsuario):

    params={'idAutor':idUsuario,'nombreAutor':nombreUsuario,'codigoUsuario':codigoUsuario}

    res=requests.post(URL+"token/registrar",json=params)

    return res.json()["status"]=="success", res.json()["message"]



def verificarToken(idUsuario,nombreUsuario,codigoToken):

    params={'idAutor':idUsuario,'nombreAutor':nombreUsuario,'codigoVerificacion':codigoToken}

    res=requests.post(URL+"token/verificar",json=params)

    return res.json()["status"]=="success", res.json()["message"]



def registrarUsuariosMasivo(archivoStr):

    while not(archivoStr[0] in ['0','1','2','3','4','5','6','7','8','9']):

        archivoStr=archivoStr[1:]

    params=[]

    usuariosLineas=archivoStr.splitlines()

    for usuario in usuariosLineas:

        params.append({

            'codigo':usuario[0:8],

            'correo':usuario[9:]

        })



    print(params)

    res=requests.post(URL+"usuarios/registrar/"+ID_ESPECIALIDAD,json=params)

    return res.json()["status"]=="success", res.json()["message"]



def registrarComandosMasivo(dictComandos):

    print("Como llega al envio a back")

    print(dictComandos)

    #return True, "xd"

    res=requests.post(URL+"comandos/registrar/"+ID_ESPECIALIDAD,json=dictComandos)

    return res.json()["status"]=="success", res.json()["message"]



def consultaPersonalizada(idUsuario,consulta):

    params={'idAutor':idUsuario,'consulta':consulta}

    res=requests.post(URL+"comandos/personalizado",json=params)

    return res.json()["status"]=="success", res.json()["message"]



def reporteIndicadores():

    '''datos= [

        {

            "nombre" : "Numéro de alumnos verificados",

            "valor" : "51"

        },

        {

            "nombre" : "Numéro de consultas hechas",

            "valor" : "10"

        },

        {

            "nombre" : "Numéro de consultas personalizadas hechas",

            "valor" : "2"

        },

        {

            "nombre" : "Numéro de consultas automáticas hechas",

            "valor" : "8"

        }

    ]

    return datos'''

    res=requests.get(URL+"reportes/indicadores/"+ID_ESPECIALIDAD)

    return res.json()["data"]



def reporteConsultasPersonalizadas():

    '''datos=[

        {

            "id": 3,

            "consulta": "Esta es una consulta :3333",

            "fecha": "24-03-2022 00:41:34",

            "codigo": "20173002",

            "nombreUsuario": "wfcchamp#3939",

            "correo": "rodriguez.diego@pucp.edu.pe"

        },

        {

            "id": 1,

            "consulta": "Esta es una consulta :3",

            "fecha": "23-03-2022 23:45:49",

            "codigo": "20173002",

            "nombreUsuario": "wfcchamp#3939",

            "correo": "rodriguez.diego@pucp.edu.pe"

        }

    ]

    return datos'''

    res=requests.get(URL+"reportes/consultas-personalizadas/"+ID_ESPECIALIDAD)

    return res.json()["data"]



def reporteUsuarios():

    '''datos=[

        {

            "id": 1,

            "idUsuarioPlataforma": "155420121744015362",

            "codigo": "20173002",

            "nombreUsuario": "wfcchamp#3939",

            "correo": "rodriguez.diego@pucp.edu.pe",

            "verificado": True

        },

        {

            "id": 2,

            "idUsuarioPlataforma": None,

            "codigo": "20151810",

            "nombreUsuario": None,

            "correo": "a20151887@pucp.edu.pe",

            "verificado": False

        },

        {

            "id": 3,

            "idUsuarioPlataforma": None,

            "codigo": "20151811",

            "nombreUsuario": None,

            "correo": "a20151881@pucp.edu.pe",

            "verificado": False

        }

    ]

    return datos'''

    res=requests.get(URL+"reportes/usuarios/"+ID_ESPECIALIDAD)

    return res.json()["data"]