from Io.moviesgroupe.movieinfoservice.model import HelloService
class HelloServicesPython(HelloService):
    def __init__(self):
        self.value="Hello Services"
    def GetHello(self):
        return self.value