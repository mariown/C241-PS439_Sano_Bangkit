# Use the official Node.js 18 base image
FROM node:20

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install application dependencies
RUN npm install
RUN npm install -g prisma

# Copy application code
COPY . .
RUN npx prisma generate
RUN npx prisma migrate dev

# Set the command to start the server
CMD ["npm", "start"]