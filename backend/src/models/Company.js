import mongoose from 'mongoose';

const schema = {
    name: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    password: {
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
            required: true,
            default: 'Point',
          },
          coordinates: {
            type: [Number],
            required: true
          }
    },
    city: {
        type: String,
        required: true,
        default: "Oulu"
    },
    country: {
        type: String,
        required: true,
        default: "Finland"
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
    },
    tags: {
        type: [String]
    }
    
}

export default mongoose.model('Company', schema);