BRANCH=$(git rev-parse --abbrev-ref HEAD)

echo "Deploying with branch: $BRANCH"

cd app/microservice-eventsourcing/
git fetch -a
git checkout $BRANCH
git pull

docker compose -f docker-compose.yml down
# Pull 2 image mới nhất từ Docker Hub
docker compose -f docker-compose.yml pull discoveryserver
docker compose -f docker-compose.yml pull bookservice

# Khởi động lại 2 container mới pull
docker compose -f docker-compose.yml up -d discoveryserver bookservice
docker system prune -af