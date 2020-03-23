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
    images: {
        type: [String]
    },
    quantity: {
        type: Number,
        required: true
    },
    tags: {
        type: String
    }
}

export default mongoose.model('Offering', schema);