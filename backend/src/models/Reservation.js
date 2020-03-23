import mongoose from 'mongoose';

const schema = {
    customer: {
        type: mongoose.Schema.Types.ObjectId,
        required: true,
        ref: 'User'
    },
    date: {
        type: Date,
        required: true
    },
    offering: {
        type: mongoose.Schema.Types.ObjectId,
        required: true,
        ref: 'Offering'
    },
    quantity: {
        type: Number,
        default: 1, 
        required: true
    }
}

export default mongoose.model('Reservation', schema);