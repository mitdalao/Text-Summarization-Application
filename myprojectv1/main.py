import os
import argparse

if __name__=='__main__':
    parser = argparse.ArgumentParser(
        description='run decoding of the full model (RL)')
    parser.add_argument('--input', required=True, help='path to store input')
    parser.add_argument('--output', required=True, help="path to store output")
    parser.add_argument('--no-cuda', action='store_true',
                        help='disable GPU training')
    parser.add_argument("--model_dir", help='path of the model',
                        default="../pretrained/pretrained/new")
    parser.add_argument('--beam_size', type=int, default=5)
    args = parser.parse_args()
    # args.cuda = torch.cuda.is_available() and not args.no_cuda
    command1 = "python ../myprojectv1/cnn-dailymail-master/make_datafiles.py %s" % args.input
    os.system(command1)
    command2 = "python ../myprojectv1/fast_abs_rl-master/decode_full_model.py --path={} " \
               "--model_dir={} --beam={} --test".format(args.output, args.model_dir, args.beam_size)
    os.system(command2)
