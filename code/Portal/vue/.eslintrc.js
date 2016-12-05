// module.exports = {
//   root: true,
//   parser: 'babel-eslint',
//   parserOptions: {
//     sourceType: 'module'
//   },
//   extends: 'airbnb-base',
//   // required to lint *.vue files
//   plugins: [
//     'html'
//   ],
//   // check if imports actually resolve
//   'settings': {
//     'import/resolver': {
//       'webpack': {
//         'config': 'build/webpack.base.conf.js'
//       }
//     }
//   },
//   // add your custom rules here
//   'rules': {
//     // don't require .vue extension when importing
//     'import/extensions': ['error', 'always', {
//       'js': 'never',
//       'vue': 'never'
//     }],
//     // allow debugger during development
//     'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0
//   }
// }


module.exports = {
  "parse":"babel-eslint",
  root: true,
  // https://github.com/feross/standard/blob/master/RULES.md#javascript-standard-style
  extends: 'eslint:recommended',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  env: {
    browser: true,
    node: true,
    es6: true,
    jquery:true
  },
  rules: {
    "indent": 0,
    "strict": 2,
    "no-unused-vars": 1,
    "no-console": 0,
    "no-debugger":0,
    "no-extra-parens":2,
    "no-extra-semi":2,
    "block-scoped-var":2,
    "consistent-return":2,
    "curly":2,
    "default-case":2,
    "dot-location":1,
    "dot-notation":1,
    "eqeqeq":2,
    "no-caller":2,
    "no-else-return":0,
    "no-empty-function":1,
    "no-eq-null":2,
    "no-eval":0,
    "no-extra-bind":2,
    "no-floating-decimal":2,
    "no-implicit-globals":2,
    "no-implied-eval":2,
    "no-invalid-this":2,
    "no-iterator":2,
    "no-labels":2,
    "no-lone-blocks":2,
    "no-loop-func":2,
    "no-multi-spaces":2,
    "no-multi-str":2,
    "no-native-reassign":2,
    "no-new":2,
    "no-new-func":2,
    "no-new-wrappers":2,
    "no-param-reassign":2,
    "no-process-env":0,
    "no-proto":2,
    "no-return-assign":2,
    "no-script-url":2,
    "no-self-compare":2,
    "no-sequences":2,
    "no-throw-literal":2,
    "no-unmodified-loop-condition":2,
    "no-unused-expressions":2,
    "no-useless-call":2,
    "no-useless-concat":2,
    "no-void":2,
    "no-warning-comments":1,
    "no-with":2,
    "radix":2,
    "wrap-iife":2,
    "vars-on-top":2,
    "yoda":2,
    "no-catch-shadow":2,
    "no-restricted-globals":2,
    "no-shadow":2,
    "no-use-before-define":0,
    "no-undef-init":2,
    "no-implicit-coercion":2,
    "no-undefined":0

    //es6
    ,"arrow-body-style":1
    ,"arrow-parens":2
    ,"constructor-super":2
    ,"no-duplicate-imports":2
    ,"no-var":2
    ,"no-useless-constructor":1
    ,"object-shorthand":0
    ,"prefer-arrow-callback":1
    ,"prefer-reflect":1
    ,"prefer-rest-params":1
    ,"prefer-spread":1
    ,"prefer-template":2
  },
  "parserOptions":{
    "ecmaVersion": 6,
    "sourceType": "module"
  },
  "globals": {
    "routeList":true
  }
}
