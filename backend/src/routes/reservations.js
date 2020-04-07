import express from 'express';
import passport from 'passport';

import Reservation from "../models/Reservation";

const router = new express.Router();

router.post('/', passport.authenticate('jwt', {session: false}), async (req, res, next) => {
    try{
        const input = req.body;
        const reservation = await Reservation.create({...input});
        return res.status(202).json({
            status: "Success",
            data: {
                reservation
            }
        })
    }
    catch(err){
        return res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        })
    }

});

router.get('/:id', async (req, res, next) => {
    try{
        const id = req.params.id;
        
        if(id){
            const reservation = await Reservation.findById(id);
            return res.status(200).json({status: "Success", data: {reservation}})
        }
        const reservations = await Reservation.find({});
        return res.status(200).json({status: "Success", data: {reservations}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        })
    }
});

router.put('/:id', passport.authenticate('jwt', {session: false}), async (req, res, next) => {
    try{
        const id = req.params.id;
        const input = req.body;
        if(!id){
            return res.status(400).json({status: "Failed", data: {message: "Please provide an id"}})
        }
        const reservation = await Reservation.findByIdAndUpdate(id, {...input}, { new: true});
        return res.status(200).json({status: "Success", data: {reservation}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        })
    }
});

router.delete('/:id', passport.authenticate('jwt', {session: false}), async (req, res, next) => {
    try{
        const id = req.params.id;
        if(!id){
            return res.status(400).json({status: "Failed", data: {message: "Please provide an id"}})
        }
        const reservation = await Reservation.findByIdAndDelete(id);
        return res.status(200).json({status: "Success", data: {reservation}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        })
    }
});

export default router;