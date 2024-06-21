# Sano Backend

## Overview

This project involves a backend server deployed on Google Cloud Run, with the database hosted on Cloud SQL. The backend is built using Express.js to provide REST API endpoints for authentication and user data management. Additionally, an ML model implemented in Flask is integrated for health predictions, ensuring seamless communication between the model and the backend.

## Tools

- **REST API with Express.js**: Handles user authentication and data management.
- **REST API ML Model with Flask**: Connect ML models to express.js.
- **Google Cloud Run**: Serverless deployment for backend and ML model.
- **Cloud SQL**: Managed database hosting.
- **Prisma ORM**: Manages database migrations and queries.

## Deployment

### Prerequisites

- Google Cloud account
- Dockerfile
- Prisma CLI installed

### Step 1: Setup Google Cloud Project

1. **Create a new project** in the Google Cloud Console.
2. **Enable the necessary APIs**:
    - Cloud Run
    - Cloud SQL
    - Artifact Registry
3. **Clone your repo in google cloud shell**
4. **Create a Cloud SQL instance**:
    - Go to the SQL section in the Google Cloud Console.
    - Create a new MySql instance.
    - Note down the connection name, database name, username, and password.
5. **Create an Artifact Registry repository**:
    - Go to the Artifact Registry section in the Google Cloud Console.
    - Create a new Docker repository.
    - Note down the repository name and location.

### Step 2: Configure Environment Variables

Create a `.env` file in the root of your project with the following variables:

```env
DATABASE_URL="postgresql://<username>:<password>@/<database>?host=/cloudsql/<connection_name>"
```

Replace `<username>`, `<password>`, `<database>`, and `<connection_name>` with your Cloud SQL instance details.

### Step 3: Deploy the Backend

1. **Dockerize the Backend**:

    Create a `Dockerfile` for your Express.js backend:

    ```Dockerfile
    FROM node:20

    WORKDIR /usr/src/app

    COPY package*.json ./
    RUN npm install
    RUN npm install -g prisma

    COPY . .
    RUN npx prisma generate
    RUN npx prisma migrate dev

    CMD ["npm", "start"]
    ```

2. **Build Docker Image**:

    ```bash
    docker build -t <image-name>:<tag> .
    ```
    Replace `<image-name>`, and `<tag>` with your specific details.

3. **Create tag to Artifact Registry and Push docker image to artifact registry**:
    ```bash
    docker tag <image_name>:<tag> <location>-docker.pkg.dev/<project_id>/<repository_name>/<image_name>:<tag>
    docker push <location>-docker.pkg.dev/<project_id>/<repository_name>/<image_name>:<tag>
    ```
    Replace `<image-name>`, `<tag>`, `<location>`, `<project_id>`, and `<repository_name>` with your specific details.

4. **Deploy cloud run by pull docker image from artifact registry**:

    ```bash
    gcloud run deploy <service-name> --image <image-uri>
    ```

    Replace `<service-name>` and `<image-uri>` with your specific details.

### Step 4: Deploy the ML Model

1. **Dockerfile the Flask App**:

    Create a `Dockerfile` for your Flask app:

    ```Dockerfile
    FROM python:3.11-slim

    ENV PYTHONUNBUFFERED True
    
    ENV APP_HOME /app
    WORKDIR $APP_HOME
    COPY . ./

    RUN pip install --no-cache-dir -r requirements.txt

    CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 main:app
    ```

2. **Build Docker Image**:

    ```bash
    docker build -t <image-name>:<tag> .
    ```
    Replace `<image-name>`, and `<tag>` with your specific details.

3. **Create tag to Artifact Registry and Push docker image to artifact registry**:
    ```bash
    docker tag <image_name>:<tag> <location>-docker.pkg.dev/<project_id>/<repository_name>/<image_name>:<tag>
    docker push <location>-docker.pkg.dev/<project_id>/<repository_name>/<image_name>:<tag>
    ```
    Replace `<image-name>`, `<tag>`, `<location>`, `<project_id>`, and `<repository_name>` with your specific details.

4. **Deploy cloud run by pull docker image from artifact registry**:

    ```bash
    gcloud run deploy <service-name> --image <image-uri>
    ```
    Replace `<service-name>` and `<image-uri>` with your specific details.

### Step 5: Migrate Database with Prisma

1. **Configure Prisma**:

    Update `prisma/schema.prisma` with the following:

    ```prisma
    datasource db {
      provider = "postgresql"
      url      = env("DATABASE_URL")
    }

    generator client {
      provider = "prisma-client-js"
    }

    // Define your models here
    ```

2. **Run Migrations**:

    ```bash
    npx prisma migrate dev
    ```

3. **Generate Prisma Client**:

    ```bash
    npx prisma generate
    ```

## Usage

### Accessing the Backend

- The backend API will be available at the URL provided by Cloud Run after deployment.

### Accessing the ML Model

- The ML model endpoint will be available at the URL provided by Cloud Run after deployment.
