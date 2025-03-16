# Use the official Node.js image to build the app
FROM node:18 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application files
COPY . .

# Build the React app for production
RUN npm run build

# Serve the app using a simple web server (e.g., serve)
FROM nginx:alpine

# Copy the build folder from the previous stage 
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80    
EXPOSE 80

# Start the nginx server
CMD ["nginx", "-g", "daemon off;"]