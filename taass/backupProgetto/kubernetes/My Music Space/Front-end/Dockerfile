# Use an official node runtime
FROM node:18-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package.json .
COPY package-lock.json .

# Install the dependencies
RUN npm install --legacy-peer-deps

# Copy the rest of the application code
COPY . .

# Expose port 5173
EXPOSE 5173

# Start the application
CMD [ "npm", "run", "dev" ]