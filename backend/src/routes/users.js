import express from 'express';

import User from "../models/User";

const router = new express.Router();

router.post('/', async (req, res, next) => {
    try{
        const input = req.body;
        const user = await User.create({...input});
        return res.status(202).json({
            status: "Success",
            data: {
                user
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
            const user = await User.findById(id);
            return res.status(200).json({status: "Success", data: {user}})
        }
        const users = await User.find({});
        return res.status(200).json({status: "Success", data: {users}})
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
        const user = await User.findByIdAndUpdate(id, {...input});
        return res.status(200).json({status: "Success", data: {user}})
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
        const user = await User.findByIdAndDelete(id);
        return res.status(200).json({status: "Success", data: {user}})
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