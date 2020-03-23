import express from 'express';

import Offering from "../models/Offering";

const router = new express.Router();

router.post('/', async (req, res, next) => {
    try{
        const input = req.body;
        const offering = await Offering.create({...input});
        return res.status(202).json({
            status: "Success",
            data: {
                offering
            }
        })
    }
    catch(err){
        return res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err
            }
        })
    }

});

router.get('/:id', async (req, res, next) => {
    try{
        const id = req.params.id;
        
        if(id){
            const offering = await Offering.findById(id);
            return res.status(200).json({status: "Success", data: {offering}})
        }
        const offerings = await Offering.find({});
        return res.status(200).json({status: "Success", data: {offerings}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err
            }
        })
    }
});

router.put('/:id', async (req, res, next) => {
    try{
        const id = req.params.id;
        const input = req.body;
        if(!id){
            return res.status(400).json({status: "Failed", data: {message: "Please provide an id"}})
        }
        const offering = await Offering.findByIdAndUpdate(id, {...input});
        return res.status(200).json({status: "Success", data: {offering}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err
            }
        })
    }
});

router.delete('/:id', async (req, res, next) => {
    try{
        const id = req.params.id;
        if(!id){
            return res.status(400).json({status: "Failed", data: {message: "Please provide an id"}})
        }
        const offering = await Offering.findByIdAndDelete(id);
        return res.status(200).json({status: "Success", data: {offering}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err
            }
        })
    }
});

export default router;