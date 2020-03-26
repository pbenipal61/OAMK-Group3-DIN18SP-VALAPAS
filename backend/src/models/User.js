import mongoose from 'mongoose';


const schema = new mongoose.Schema({
    firstName: {
        type: String,
        required: [true, 'First name is required']
    },
    lastName: {
        type: String,
        required: [true, 'Last name is required']
    },
    isAdult: {
        type: Boolean,
        required: true,
        default: false,
    },
    email: {
        type: String,
        unique: true,
        required: true
    },
    password: {
        type: String,
        required: true,
    },
    city: {
        type: String,
        default: "Oulu",
        required: true,
    },
});

const User = mongoose.model('User', schema);
export default User;