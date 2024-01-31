// parseUser.js
const parseUser = (user) => {
    let userArray = [];
    userArray = user.split(' -> ');
    userArray[0] = userArray[0].substring(9);
    userArray[userArray.length - 1] = userArray[userArray.length - 1].substring(0, userArray[userArray.length - 1].length - 1);
    return userArray;
};

export default parseUser;