import mongoose from 'mongoose';

const schema = {
    name: {
        type: String,
        required: true
    },
    description: {
        type: String,
        required: true
    },
    images: {
        type: [String]
    },
    address: {
        type: String,
        required: true
    },
    postalCode: {
        type: Number,
        required: true
    },
    location: {
        type: {
            type: String, // Don't do `{ location: { type: String } }`
            enum: ['Point'], // 'location.type' must be 'Point'
            required: [true, 'GPS location of the store must be provided'],
          },
          required: [true, 'GPS location of the store must be provided'],
    },
    city: {
        type: String,
        required: true
    },
    country: {
        type: String,
        required: true
    },
    categories: {
        type: [String],
        required: true
    },
    openingHours: {
        type: [[Number]], // [[opening time, closing time], [next day]]
        required: true
    },
    priceRange: {
        type: [Number], // [min, max]
        required: true
    }
    
}

export default mongoose.model('Company', schema);