import discord
from discord.ext import commands

class Example(commands.Cog):
    def __init__(self,client):
        self.client=client
    
    '''#eventos
    @commands.Cog.listener()
    async def on_ready(self): #para los cog self es el primer argumento siempre
        await self.client.change_presence(activity=discord.Activity(type=discord.ActivityType.watching,name="a enma"))
        print('Bot esta listo')'''

    #comandos
    @commands.command()
    async def ping(self,ctx):
        await ctx.send(f'Pong! {round(self.client.latency*1000)}ms')
        #await ctx.send(f'Pong!')
        

def setup(client):
    client.add_cog(Example(client))