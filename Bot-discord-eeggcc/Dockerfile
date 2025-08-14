FROM python:3.10-alpine

WORKDIR /app

# Instalar dependencias necesarias con pip
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copiar el código fuente
COPY . .

EXPOSE 5000

CMD ["python", "bot.py"]
