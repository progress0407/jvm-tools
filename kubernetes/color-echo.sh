# Color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color or End Of Color
END=$NC

echo_red() {
    echo -e "${RED}$1${END}"
}

echo_green() {
    echo -e "${GREEN}$1${END}"
}

echo_blue() {
  echo -e "${BLUE}$1${END}"
}
