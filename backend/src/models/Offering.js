import mongoose from 'mongoose';

const schema = {
    company:{
        type: mongoose.Schema.Types.ObjectId,
        required: true,
        ref: 'Company'
    },
    offeringType: {
        required: true,
        type: String
    },
    description: {
        type: String,
        required: true,
    },
    images: {
        type: [String]
    },
    quantity: {
        type: Number,
        required: true
    },
    tags: {
        type: String
    },
    price: {
        type: Number,
        required: true
    },
    deposit:{
        type: Number,
    },
    discounts: {
        type: [String]
    },
}

export default mongoose.model('Offering', schema);